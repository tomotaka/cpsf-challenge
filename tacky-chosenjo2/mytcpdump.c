#include <sys/types.h>
#include <netinet/in.h>
#include <pcap.h>

struct eth_header {
    unsigned char ether_dhost[6];
    unsigned char ether_shost[6];
    unsigned short ether_type;
};

struct ip4_header {
    unsigned char version_and_header_length;    // 1
    unsigned char service_type;                 // 1 (total 2)
    unsigned short length;                      // 2 (total 4)
    unsigned short identification;              // 2 (total 6)
    unsigned short flags_and_fragment_offset;   // 2 (total 8)
    unsigned char ttl;                          // 1 (total 9)
    unsigned char protocol;                     // 1 (total 10)
    unsigned short checksum;                    // 2 (total 12)
    unsigned char source_ip_addr[4];            // 4 (total 16)
    unsigned char destination_ip_addr[4];       // 4 (total 20)
};

struct tcp_header {
    unsigned short source_port;      // 2
    unsigned short destination_port; // 2 (total 4)
    unsigned int seq;                // 4 (total 8)
    unsigned int ack;                // 4 (total 12)
    unsigned short bits;             // 2 (total 14)
    unsigned short window_size;      // 2 (total 16)
    unsigned short checksum;         // 2 (total 18)
    unsigned short urgent_pointer;   // 2 (total 20)
};

void print_mac_addr(unsigned char *mac_addr) {
    int i;
    for (i = 0; i < 6; i++) {
        printf("%02x", (int)mac_addr[i]);
        if (i < 5) {
            printf(":");
        }
    }
}

void my_callback(u_char *args, const struct pcap_pkthdr* header, const u_char* packet) {
    int tcp_header_length, tcp_option_length, payload_length;
    unsigned short tcp_dport, tcp_sport;
    struct tcp_header* tcp_packet_header;
    unsigned char* payload;
    struct eth_header* ethernet_frame_header = (struct eth_header*)packet;
    //printf("sizeof(struct eth_header)=%ld\n", sizeof(struct eth_header));
    //printf("sizeof(struct ip4_header)=%ld\n", sizeof(struct ip4_header));
    //printf("sizeof(struct tcp_header)=%ld\n", sizeof(struct tcp_header));
    struct ip4_header* ip_packet_header = (struct ip4_header*)(packet + sizeof(struct eth_header));
    //printf("ip-packet-length=%d\n", ip_packet_header->length);


    printf("-----------\n");
    printf("MAC: ");
    print_mac_addr(ethernet_frame_header->ether_shost);
    printf(" => ");
    print_mac_addr(ethernet_frame_header->ether_dhost);
    printf("\n");

    //printf("version_and_header_length=0x%02x\n", (int)ip_packet_header->version_and_header_length);
    unsigned char version = (0xF0 & ip_packet_header->version_and_header_length) >> 4;
    if (version == 6) {
        printf("skip IPv6 packet...\n");
        return;
    }

    printf("version=%d\n", version);
    unsigned char header_length = 4 * (0x0F & ip_packet_header->version_and_header_length);
    int option_length = (((int)header_length) - 20);
    //printf("option_length=%d(header_length=%d)\n", option_length, header_length);

    switch (ip_packet_header->protocol) {
        case 0:
            printf("protocol: IP\n");
            break;

        case 1:
            printf("protocol: ICMP\n");
            break;

        case 6:
            printf("protocol: TCP\n");
            tcp_packet_header = (struct tcp_header*)(packet + sizeof(struct eth_header) + sizeof(struct ip4_header) + option_length);
            tcp_dport = ntohs(tcp_packet_header->destination_port);
            tcp_sport = ntohs(tcp_packet_header->source_port);
            //printf("TCP port: source(%d) => destination(%d)\n", tcp_packet_header->source_port, tcp_packet_header->destination_port);
            printf("TCP port: source(%d) => destination(%d)\n", tcp_sport, tcp_dport);
            tcp_header_length = 4 * (15 & tcp_packet_header->bits);
            tcp_option_length = tcp_header_length - 20;
            printf("tcp_option_length=%d\n", tcp_option_length);
            payload_length = ip_packet_header->length - sizeof(struct ip4_header) - option_length - sizeof(struct tcp_header) - tcp_option_length;
            payload = (unsigned char*)(packet + sizeof(struct eth_header) + sizeof(struct ip4_header) + option_length + sizeof(struct tcp_header) + tcp_option_length);
            write(1, payload, payload_length);
            break;

        case 17:
            printf("protocol: UDP\n");
            break;

    }
}

int main(int argc, char** argv) {
    pcap_t* pd;
    bpf_u_int32 localnet, netmask;
    char ebuf[PCAP_ERRBUF_SIZE];
    int snaplen = 64;
    int pflag = 0;
    int timeout = 1000;

    if (argc < 2) {
        printf("give interface\n");
        return -1;
    }

    if ((pd = pcap_open_live(argv[1], snaplen, !pflag, timeout, ebuf)) == NULL) {
        printf("error: pcap_open_live(%s)\n", argv[1]);
        return -1;
    }

    if (pcap_lookupnet(argv[1], &localnet, &netmask, ebuf) < 0) {
        printf("error: pcap_loop(%s)\n", argv[1]);
        return -1;
    }

    if (pcap_loop(pd, -1, my_callback, NULL) < 0) {
        printf("error: pcap_loop\n");
        return -1;
    }

    pcap_close(pd);

    return 0;
}

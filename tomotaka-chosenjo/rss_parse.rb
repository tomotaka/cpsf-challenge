# coding: utf-8
#
# Sample of parsing RSS

require "open-uri"
require "rubygems"
require "nokogiri"

url = "http://gyao.yahoo.co.jp/rss/ranking/c/daily/movie/"

rss = Nokogiri::XML(open(url))

rss.xpath("/rss/channel/item").each do |item|
  puts "----------"
  title = item.xpath("./title/text()").to_a.first.to_s
  link = item.xpath("./link/text()").to_a.first.to_s
  description = item.xpath("./description/text()").to_a.first.to_s
  pub_date = item.xpath("./pubDate/text()").to_a.first.to_s


  puts "Title       : #{title}"
  puts "Link        : #{link}"
  puts "Description : #{description}"
  puts "PubDate     : #{pub_date}"

  puts "\n\n"
end



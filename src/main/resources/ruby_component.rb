require 'rubygems'
require 'stomp'

puts 'ruby component is up and running ...'
client = Stomp::Client.open "stomp://localhost:61613"
client.subscribe "/queue/java2ruby", {:ack => :client} do |message|
  puts "#{message.body}"
  client.acknowledge message
end
client.join
client.close
puts 'ruby component is stoped'
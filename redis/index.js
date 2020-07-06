const redis = require("redis");
const client = redis.createClient();

client.on("error", function(error) {
  console.error(error);
});

client.set("orders", "order from customer", redis.print);
client.get("orders", redis.print);
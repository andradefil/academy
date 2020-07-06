module.exports = {
    listItems: listItems
}

function listItems() {
    docClient.query({
        TableName: global.tableName,
        KeyConditionExpression: "customer_reference = :customerReference",
        ExpressionAttributeValues: {
            ":customerReference": "5665cb89-3392-4dd9-b1d1-9e6eb0fbd34b"
        }
    }, (err, data)=>{
        if(err) {
            console.log(err);
        } else {
            console.log("reads data -> ", data.Count)
            // console.log(data);
        }
    });
}

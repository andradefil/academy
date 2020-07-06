module.exports = {
    listItems: listItems
}

function listItems() {
    docClient.query({
        TableName: global.tableName,
        KeyConditionExpression: "customer_reference = :customerReference",
        ExpressionAttributeValues: {
            ":customerReference": "4A29C9E7-6A72-4905-9828-700DA63CC0DE"
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

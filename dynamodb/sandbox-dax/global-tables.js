const AWS = require("aws-sdk");
AWS.config.update({ region: 'us-west-2' });

const docClient = new AWS.DynamoDB.DocumentClient();

docClient.put({
    TableName: 'global_td_notes',
    Item: {
        user_id: 'ABC',
        timestamp: 5,
        title: 'ABC title',
        content: 'ABC content'
    }
}, (err, data)=>{
    if(err) {
        console.log(err);
    } else {
        console.log(data);
        console.log('Put operation successful in', AWS.config.region);

        setTimeout(()=>{
            AWS.config.update({ region: 'eu-central-1' });

            docClient.get({
                TableName: "global_td_notes",
                Key: {
                    user_id: 'ABC',
                    timestamp: 5
                }
            }, (err, data)=>{
                if(err) {
                    console.log(err);
                } else {
                    console.log('Getting the item from', AWS.config.region);
                    console.log(data);
                }
            });
        }, 1000);
    }
});
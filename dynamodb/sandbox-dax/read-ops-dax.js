const AWS = require("aws-sdk");
AWS.config.update({ region: 'us-west-2' });

const AmazonDaxClient = require("amazon-dax-client");
const dynamodb = new AmazonDaxClient({
    endpoints: ['dax-notes-app.hllvre.clustercfg.dax.usw2.cache.amazonaws.com:8111'],
    region: 'us-west-2'
});

// const dynamodb = new AWS.DynamoDB();
dynamodb.getItem({
    TableName: "td_notes_test",
    Key: {
        user_id: {
            S: "A"
        },
        timestamp: {
            N: "1"
        }
    }
}, (err, data)=>{
    if(err) {
        console.log(err);
    } else {
        console.log(data);
    }
});

// const docClient = new AWS.DynamoDB.DocumentClient();

// docClient.get({
//     TableName: 'td_notes_test',
//     Key: {
//         user_id: 'A',
//         timestamp: 1
//     }
// }, (err, data)=>{
//     if(err) {
//         console.log(err);
//     } else {
//         console.log(data);
//     }
// });

// docClient.query({
//     TableName: 'td_notes_test',
//     KeyConditionExpression: "user_id = :uid",
//     ExpressionAttributeValues: {
//         ":uid": "A"
//     }
// }, (err, data)=>{
//     if(err) {
//         console.log(err);
//     } else {
//         console.log(data);
//     }
// });

// docClient.scan({
//     TableName: 'td_notes_test',
//     FilterExpression: "cat = :cat",
//     ExpressionAttributeValues: {
//         ":cat": "general"
//     }
// }, (err, data)=>{
//     if(err) {
//         console.log(err);
//     } else {
//         console.log(data);
//     }
// });


// docClient.batchGet({
//     RequestItems: {
//         'td_notes_test': {
//           Keys: [
//             {
//                user_id: 'A',
//                timestamp: 1
//             },
//             {
//                 user_id: 'B',
//                 timestamp: 2
//             }
//           ]
//         },
//         'td_notes_sdk': {
//           Keys: [
//             { 
//                 user_id: '11',
//                 timestamp: 1
//             }
//           ]
//         }
//     }
// }, (err, data)=>{
//     if(err) {
//         console.log(err);
//     } else {
//         console.log(JSON.stringify(data, null, 2));
//     }
// });
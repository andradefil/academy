const AWS = require("aws-sdk");
AWS.config.update({ region: 'us-west-2' });

const docClient = new AWS.DynamoDB.DocumentClient();

const faker = require('faker');
const moment = require('moment');

setInterval(()=>{
    let params = {
        TableName: "global_td_notes"
    };

    generateNotesItem((item)=>{
        params.Item = item;
        docClient.put(params, (err, data)=>{
            if(err) {
                console.log(err);
            } else {
                console.log(data);
            }
        });
    });
}, 300);

function generateNotesItem(callback) {
    callback({
        user_id: faker.random.uuid(),
        timestamp: moment().unix(),
        cat: faker.random.word(),
        title: faker.company.catchPhrase(),
        content: faker.hacker.phrase(),
        note_id: faker.random.uuid(),
        user_name: faker.internet.userName(),
        expires: moment().unix() + 600
    });
}
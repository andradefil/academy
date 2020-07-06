const utils = require('../utils/data')

module.exports = {
    createItem: createItem,
    createItemBatch: createItemBatch
}

function createItem() {
    const item = utils.getRandomItem()
    docClient.put({
        TableName: global.tableName,
        Item: item
    }, (err, data) => {
        if (err) {
            console.log(err);
        } else {
            console.log("created item -> ", item);
        }
    });
}

function createItemBatch() {
    docClient.batchWrite({
        RequestItems: {
            [global.tableName]: [
                {
                    PutRequest: {
                        Item: {
                            "customer_reference": "5665cb89-3392-4dd9-b1d1-9e6eb0fbd34b",
                            "year_month_day_hh_mm_ss": "2020|04|25|14|01|00",
                            "epoch_time": 1603645260,
                            "payment_id": "bfe26f5a-5f0d-42b7-8a09-a5d1cb968c0e",
                            "band": "VISA",
                            "payment_status": "AUTHORIZED",
<<<<<<< HEAD
                            "total_ammount": 6000,
=======
>>>>>>> 6448581 (Adds content)
                            "ttl": 1603645200
                        }
                    }
                }
            ]
        }
    }, (err, data) => {
        if (err) {
            console.log(err);
        } else {
            console.log(data);
        }
    });
}

const faker = require('faker');
const moment = require('moment');

module.exports = {
    getRandomItem: getRandomItem
}

function getRandomItem() {
<<<<<<< HEAD
=======
    const cardAmount = faker.finance.amount()
>>>>>>> 40105ef (Adds content)
    const totalAmount = walletAmount + cardAmount
    const paymentStatus = faker.random.arrayElement(['AUTHORIZED', 'NOT_AUTHORIZED'])
    const paymentId = faker.random.uuid()
    const date = moment(faker.date.between('2020-05-08T14:00:00', '2020-05-08T15:00:00'));
    const epochTime = date.unix()
    const yearMonthDay = `${date.year()}|${date.month()}|${date.format('DD')}|${date.hour()}|${date.minute()}|${date.second()}`

    return {
        "customer_reference": "5665cb89-3392-4dd9-b1d1-9e6eb0fbd34b",
        "year_month_day_hh_mm_ss": yearMonthDay,
        "epoch_time": epochTime,
<<<<<<< HEAD
        "payment_id": paymentId,
=======
>>>>>>> 6448581 (Adds content)
        "band": "VISA",
        "date": date.format("YYYY-MM-DDTHH:mm:ss"),
        "payment_status": paymentStatus,
        "total_ammount": totalAmount,
        "ttl": 1603645200
    }
}

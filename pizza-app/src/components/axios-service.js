import axios from 'axios';

export const zipcode = ()=> {
    return axios.get('http://localhost:8080/zipcode')
    .then(response => response.data)
    .catch(error => {
                throw error;
              });
};

export const getPizzas = ()=> {
    return axios.get('http://localhost:8080/pizza')
    .then(response => response.data)
    .catch(error => {
                throw error;
              });
};


export const addOrder = (orderDetail,totalPrice)=> {
    return axios.post('http://localhost:8081/order',{
    "deliveryInfo": [{
    		"firstName": orderDetail.firstName,
    		"lastName": orderDetail.lastName,
    		"email": orderDetail.email,
    		"contactNo": orderDetail.contactNum,
    		"address": orderDetail.address
    	}],
    	"totalCost": totalPrice
  }).then(response => response.data)
    .catch(error => {
                throw error;
              });
};


export const addPayment = (paymentDetail,orderId,totalCost)=> {
    return axios.post('http://localhost:8082/payment',{
   "creditCard" :
        {
        "cardNumber":paymentDetail.cardNumber
        },
   	"orderId":orderId,
   	"amount":totalCost,
   	"paymentStatus":"PENDING"
  }).then(response => response.data)
    .catch(error => {
              console.log("error--",error.response.data.message);
              throw error;
              });
};

import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Form, Button, Segment, Modal } from 'semantic-ui-react';

class PaymentForm extends Component {
    state = {
     paymentDetails: PropTypes.object,
     updatePaymentDetails: PropTypes.func.isRequired,
    }

     handleChange = (e) => {
        const updatePayments = {
          ...this.props.paymentDetails,
          [e.currentTarget.name]: e.target.value
        }
        this.props.updatePaymentDetails(updatePayments);
      }


    render(){
    //const { cardNumber, expirationDate, cvc} = this.props.paymentDetails;
      return( <div>
                   <Modal trigger={<Button size='tiny' color='teal'><span role='img' aria-label='credit-card'>💳 </span>Use test card</Button>} closeIcon>
                   <Modal.Header>Test Card Details</Modal.Header>
                             <Modal.Content>
                               <Modal.Description>
                                 <p><strong>Card number:</strong> 4242424242424242</p>
                                 <p><strong>Expiration date:</strong> 12/20</p>
                                 <p><strong>CVC:</strong> 123</p>
                               </Modal.Description>
                             </Modal.Content>
                           </Modal>
                   <Segment>
                     <Form id='checkout-payment-form'>
                         <Form.Input
                           fluid
                           label='Card number'
                           type='text'
                           placeholder='4242424242424242'
                           name='cardNumber'
                           //value={cardNumber}
                           onChange={this.handleChange}
                           required />

                       <Form.Input
                         label='Expiration date'
                         type='text'
                         placeholder='10/20'
                         name='expirationDate'
                         //value ={expirationDate}
                         onChange={this.handleChange}
                         required />

                       <Form.Input
                         label='CVC'
                         type='text'
                         //value={cvc}
                         placeholder='456'
                         name='cvc'
                         onChange={this.handleChange}
                         required />
                     </Form>
                   </Segment>
                 </div>);
    }
}
export default PaymentForm;
import React, { Component, Fragment } from 'react';
import { Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import { Grid, Container, Header, Button } from 'semantic-ui-react';
import { formatPrice } from '../helpers';

import NavBar from './NavBar';
import CustomerDetailsForm from './CustomerDetailsForm';
import PaymentForm from './PaymentForm';
import Footer from './Footer';
import * as AxiosAPI from '../components/axios-service.js';

class Checkout extends Component {

  static propTypes = {
    orderTotal: PropTypes.number,
    customerDetails: PropTypes.object,
    loadSampleCustomer: PropTypes.func.isRequired,
    updateCustomerDetails: PropTypes.func.isRequired,
    checkoutTotal: PropTypes.number,
    paymentDetails: PropTypes.object,
    updatePaymentDetails: PropTypes.func.isRequired,
  }

  state = {
    customerForm: false,
    paymentForm: false,
    completedForm: false,
    orderStatus:'PENDING'
  }

  checkPaymentForm = (bool) => {
    this.setState({ paymentForm: bool});
  }

  checkCustomerForm = () => {
    const { firstName, lastName, email, contactNum, address } = this.props.customerDetails;
    if (firstName && lastName && email && contactNum && address) {
      this.setState({ customerForm: true });
    } else {
      this.setState({ customerForm: false });
    }
  }

  handleSubmit = () => {
    this.checkCustomerForm();

    AxiosAPI.addOrder(this.props.customerDetails,this.props.checkoutTotal).then((res)=>{
         console.log("response--",res);
         AxiosAPI.addPayment(this.props.paymentDetails,res.id,this.props.checkoutTotal).then((res)=>{
                         this.setState({ orderStatus: res.status });
                        }).catch(error => {
                        this.setState({ orderStatus: error.response.data.status });
                        });
        });


    if(this.state.customerForm && this.state.orderStatus === 'APPROVED') {
      this.setState({ completedForm: true });
    } else {
      this.setState({ completedForm: false });
    }
  }

  render(){
    if (this.state.customerForm && this.state.orderStatus === 'APPROVED') {
    return <Redirect push to='/confirmed' />;
    }else if(this.state.customerForm && this.state.orderStatus === 'TIMEOUT'){
    return <Redirect push to='/failure' />;
    }

    return(
      <Fragment>
        <NavBar orderTotal={this.props.orderTotal}/>
        <Container id='page-container'>
          <Header as='h1' id="page-header">Checkout</Header>
          <Grid stackable columns={2}>
            <Grid.Column width={10}>
              <Header as='h3' id='checkout-subheader'>Your Details</Header>
              <CustomerDetailsForm
                customerDetails={this.props.customerDetails}
                updateCustomerDetails={this.props.updateCustomerDetails}
                loadSampleCustomer={this.props.loadSampleCustomer}
                formStatus={this.checkCustomerForm}
              />
            </Grid.Column>
            <Grid.Column width={6}>
              <Header as='h3' id='checkout-subheader'>Payment</Header>
              <PaymentForm paymentDetails={this.props.paymentDetails}
              updatePaymentDetails={this.props.updatePaymentDetails}
              formStatus={this.checkCustomerForm}/>
              <Button color='teal' size='large' id='checkout-btn' onClick={this.handleSubmit}>Place Order & Pay {formatPrice(this.props.checkoutTotal)} </Button>
            </Grid.Column>
          </Grid>
        </Container>
        <Footer />
      </Fragment>
    )
  }
}

export default Checkout;


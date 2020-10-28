import React, { Component, Fragment } from 'react';
import { Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import { Container, Header, Menu, Button } from 'semantic-ui-react';

import Footer from './Footer';

class Failure extends Component {
    static propTypes = {
    customerDetails: PropTypes.object,
    clearState: PropTypes.func.isRequired
  }

  state = {
    newOrder: false
  }

 handleClick = () => {
    this.props.clearState();
    this.setState({ newOrder: true });
  }

render(){
if(this.state.newOrder) {
      return <Redirect push to='/' />;
    }
 return(<Fragment>
             <Menu secondary id='navbar'>
               <Menu.Item header id='navbar-header'>Pizzeria</Menu.Item>
             </Menu>
               <Container textAlign='center' id='failure-container'>
                 <Header as='h1' id='page-header'>Something went wrong!! </Header>

                 <Button onClick={this.handleClick} color='teal' size='large' id='confirmation-new-btn'>Start a fresh order</Button>
               </Container>
               <Footer />
             </Fragment>
           )
    }
}
export default Failure;
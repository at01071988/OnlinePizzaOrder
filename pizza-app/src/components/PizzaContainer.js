import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import Pizza from './Pizza';
import * as AxiosAPI from '../components/axios-service.js';

class PizzaContainer extends Component {

static propTypes = {
  addToOrder: PropTypes.func.isRequired
};

state = {
  pizzaList : ''
}
componentDidMount(){
    AxiosAPI.getPizzas().then((res)=>{
       this.setState({pizzaList : res});
      });
}

render(){
    let pizzas = Object.keys(this.state.pizzaList).map( key => {
      return <Pizza
                  key={key}
                  details={this.state.pizzaList[key]}
                  addToOrder={this.props.addToOrder}
                  index={key}
                />
                });
      return (
          <Grid stackable columns={3}>
            {pizzas}
          </Grid>
      )
    }
}
export default PizzaContainer;
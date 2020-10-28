export function formatPrice(cents) {
  return (cents).toLocaleString("en-EU", {
    style: "currency",
    currency: "EUR"
  });
}

export function formatToppings(arr){
 const joined = arr.join(', ');
 return joined.charAt(0).toUpperCase() + joined.slice(1);
}

<template>
  <br />
  <h3>Product</h3>
  <br />
  Name: <input type="text" name="" id="1" v-model="name" /> <br />
  Price: <input type="text" name="" id="2" v-model="price" /> <br /><br />

  <button @click="createProduct">Create</button>
  | <button @click="fetchProducts">Fetch All</button><br /><br />

  Id: <input type="text" name="" id="0" v-model="id" /> <br /><br />

  <button @click="deleteProduct">Delete</button>
  | <button @click="putProduct">Update</button> |
  <button @click="fetchByIdProduct">Fetch</button><br /><br />

  {{ product }} <br />

  <ul>
    <li v-for="product in products" :key="product.id">
      Id: <a :href="'/feedback/' + product.id"> {{ product.id }} </a><br />
      Name: {{ product.name }} <br />
      Price: {{ product.price }} <br />
    </li>
  </ul>
</template>

<script>
import axios from "axios";

export default {
  name: "Home",
  data() {
    return {
      id: "",
      price: "",
      name: "",
      products: [],
      product: null,
      baseURI: "http://localhost:31000/api/products",
    };
  },
  methods: {
    fetchProducts: function() {
      axios.get(this.baseURI).then((result) => {
        console.log(result);
        this.products = result.data;
      });
    },
    fetchByIdProduct: function() {
      axios.get(this.baseURI + "/" + this.id).then((result) => {
        console.log(result);
        this.product = result.data;
      });
    },
    createProduct: function() {
      axios
        .post(this.baseURI, {
          name: this.name,
          price: this.price,
        })
        .then((result) => {
          alert("Add product !!");
        })
        .catch((error) => {
          if (error.response.status == 400) {
            alert("Incorrect data !!");
          } else {
            alert("Unknown problem !!");
          }
        });
    },
    putProduct: function() {
      axios
        .put(this.baseURI + "/" + this.id, {
          name: this.name,
          price: this.price,
        })
        .then((result) => {
          console.log(result.data);
        });
    },
    deleteProduct: function() {
      axios.delete(this.baseURI + "/" + this.id).then((result) => {
        console.log(result);
      });
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

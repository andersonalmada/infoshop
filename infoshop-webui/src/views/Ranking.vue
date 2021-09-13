<template>
  <br />
  <h3>Ranking</h3>
  <br />
  <ul>
    <li v-for="product in products" :key="product.id">
      ProductId: {{ product.productId }} <br />
      Evaluation: {{ product.evaluation }} <br />
    </li>
  </ul>
</template>

<script>
import axios from "axios";

export default {
  name: "Ranking",
  data() {
    return {
      products: [],
      feedbacks: [],
      baseURIFeedback: "http://localhost:8081/api/feedbacks",
      baseURIRanking: "http://localhost:8082/api/ranking",
    };
  },
  created() {
    axios.get(this.baseURIFeedback).then((result) => {
      console.log(result);
      this.feedbacks = result.data;

      axios.post(this.baseURIRanking, this.feedbacks).then((result) => {
        console.log(result);
        this.products = result.data;
      });
    });
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

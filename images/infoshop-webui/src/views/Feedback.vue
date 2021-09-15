<template>
  <br />
  <h3>Feedback</h3>
  <br />
  Comment: <input type="text" name="" id="1" v-model="comment" /> <br />
  Evaluation: <input type="text" name="" id="2" v-model="evaluation" />
  <br /><br />

  <button @click="createFeedback">Create</button>
  | <button @click="fetchFeedbacks">Fetch All</button><br /><br />

  Id: <input type="text" name="" id="0" v-model="id" /> <br /><br />

  <button @click="deleteFeedback">Delete</button>
  | <button @click="putFeedback">Update</button>

  <ul>
    <li v-for="feedback in feedbacks" :key="feedback.id">
      Id: {{ feedback.id }} <br />
      Comment: {{ feedback.comment }} <br />
      Evaluation: {{ feedback.evaluation }} <br />
    </li>
  </ul>
</template>

<script>
import axios from "axios";

export default {
  name: "Feedback",
  data() {
    return {
      id: "",
      productId: "",
      comment: "",
      evaluation: "",
      feedbacks: [],
      baseURI: "http://localhost:31001/api/feedbacks",
    };
  },
  methods: {
    fetchFeedbacks: function() {
      axios.get(this.baseURI + "/product/" + this.productId).then((result) => {
        console.log(result);
        this.feedbacks = result.data;
      });
    },
    createFeedback: function() {
      axios
        .post(this.baseURI, {
          productId: this.productId,
          comment: this.comment,
          evaluation: this.evaluation,
        })
        .then((result) => {
          if (result.status == 201) {
            alert("Add feedback !!");
          }
        })
        .catch((error) => {
          if (error.response.status == 400) {
            alert("Incorrect data !!");
          } else {
            alert("Unknown problem !!");
          }
        });
    },
    putFeedback: function() {
      axios
        .put(this.baseURI + "/" + this.id, {
          comment: this.comment,
          evaluation: this.evaluation,
        })
        .then((result) => {
          console.log(result.data);
        });
    },
    deleteFeedback: function() {
      axios.delete(this.baseURI + "/" + this.id).then((result) => {
        console.log(result);
      });
    },
  },
  created() {
    this.productId = this.$route.params.id;
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

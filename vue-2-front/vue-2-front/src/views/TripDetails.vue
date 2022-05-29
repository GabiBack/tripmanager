<template>
<div>
  <b-navbar type="dark" variant="info">
    <b-navbar-brand href="#">TripManager</b-navbar-brand>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">
        <b-nav-item href="#">Twoje podroze</b-nav-item>
        <b-nav-item href="#" >Szczegoly</b-nav-item>

      </b-navbar-nav>
  </b-navbar>
<div :style="{backgroundImage:`url(${Background})`}" class="helloUserBackground container-fluid">
<div class="row">
    <h1>Trip details</h1>
</div>
</div>
</div>
</template>
<script>
/* eslint-disable */
import Background from '../assets/p2-background.jpg'
import Routesvg from '../assets/route.svg'
// import { ref } from 'vue'
// const inputVal = ref('')
const inputVal = {value: 0};

function handleChange(e) {
  inputVal.value = e.target.value
}
async function handleClick() {
  if (inputVal.value) {
    const response = await fetch('https://api.github.com/repositories/19438/issues');
    const body = await response.json();
    console.log(body)
    this.$router.push('Home')
  }
}
export default {
  components: {
  },
  methods: {
    handleChange,
    handleClick,
  },
  data: function () {
    return {
        Background: Background,
        Routesvg: Routesvg
            }
  }}
  </script>
  <style scoped>
  .tripUserBackground{
    background-repeat: no-repeat;
    background-size: cover;
  }
.small {width: 90%;
height: 90%;
background-size: 50%;
background-position: 0% 75%;
}


  .inputContainer {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 7em;
  }
  .inputContainer label{
    font-size: 25px;
    font-weight: bold;
  }
  .buttonContainer {
    margin-top: 3em;
    width: 100%;
    display: flex;
    justify-content: flex-end;
  }
  label{
      padding: 1.5em;
  }
</style>
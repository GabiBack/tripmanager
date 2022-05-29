<template>
<div :style="{backgroundImage:`url(${Background})`}" class="helloUserBackground container-fluid">
<div class="row">
    <h1>Your trip</h1>

<div class="col-md-4">
  <b-card
    title="Card Title"
    img-src="https://picsum.photos/600/300/?image=25"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 20rem;"
    class="mb-2"
  >
    <b-card-text>
      Some quick example text to build on the card title and make up the bulk of the card's content.
    </b-card-text>

    <b-button href="#" variant="primary">Go somewhere</b-button>
  </b-card>
</div>
<div class="col-md-4">
  <b-card
    title="Card Title"
    img-src="https://picsum.photos/600/300/?image=25"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 20rem;"
    class="mb-2"
  >
    <b-card-text>
      Some quick example text to build on the card title and make up the bulk of the card's content.
    </b-card-text>

    <b-button href="#" variant="primary">Go somewhere</b-button>
  </b-card>
</div>
<div class="col-md-4">
  <b-card
    title="Card Title"
    img-src="https://picsum.photos/600/300/?image=25"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 20rem;"
    class="mb-2"
  >
    <b-card-text>
      Some quick example text to build on the card title and make up the bulk of the card's content.
    </b-card-text>

    <b-button href="#" variant="primary">Go somewhere</b-button>
  </b-card>
</div>
<div class="row">
Dodaj podroz
</div>
<div class="row">
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group
        id="input-group-1"
        label="Miejsce"
        label-for="input-1"
        description=""
      >
        <b-form-input
          id="input-1"
          v-model="form.location"
          type="text"
          placeholder="miejsce"
          required
        ></b-form-input>
      </b-form-group>
            <b-form-group
        id="input-group-2"
        label="Dzien rozpoczecia:"
        label-for="input-2"
        description=""
      >
        <b-form-input
          id="input-2"
          v-model="form.start"
          type="date"
          placeholder="YYYY-mm-dd"
          required
        ></b-form-input>
      </b-form-group>
            <b-form-group
        id="input-group-3"
        label="Dzien zakonczenia:"
        label-for="input-3"
        description=""
      >
        <b-form-input
          id="input-3"
          v-model="form.end"
          type="date"
          placeholder="YYYY-mm-dd"
          required
        ></b-form-input>
      </b-form-group>

                  <b-form-group
        id="input-group-4"
      >

                    <b-button type="submit" variant="primary">Dalej</b-button>
</b-form-group>
      </b-form>

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
          onSubmit(event) {
        event.preventDefault()
        alert(JSON.stringify(this.form))
      },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.form.start = ''
        this.form.end = ''
        this.form.location = ''
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
  },
  data: function () {
    return {
        Background: Background,
        Routesvg: Routesvg,
                form: {
          location: '',
          start: '',
          end: ''
                  },
                  show: true
    }
  }}
  </script>
  <style scoped>
  .helloUserBackground{
    width: 100vw;
    height: 100vh;
    background-repeat: no-repeat;
    background-size: cover;
    display: flex;
    align-items: center;
    justify-content: center;

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
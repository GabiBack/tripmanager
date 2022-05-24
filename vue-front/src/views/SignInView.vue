<template>
  <div :style="{backgroundImage:`url(${Background})`}" class="signInBackground">
    <div class="inputContainer">
      <label>
        Podaj swój login
      </label>
      <custom-input @handle-change="handleChange" />
      <div class="buttonContainer">
        <custom-button @handle-click="handleClick" label="Dalej"/>
      </div>
    </div>
  </div>
</template>

<script>
import Background from '../assets/signInBackground.png'
import CustomInput from '../components/CustomInput.vue'
import CustomButton from '../components/CustomButton.vue'
import { ref } from 'vue'
const inputVal = ref('')

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
    CustomInput,
    CustomButton,
  },
  methods: {
    handleChange,
    handleClick,
  },
  data: function () {
    return {
        Background: Background
    }
  }
}

</script>

<style scoped>
  .signInBackground{
    width: 100vw;
    height: 100vh;
    background-repeat: no-repeat;
    background-size: cover;
    display: flex;
    align-items: center;
    justify-content: center;
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
</style>
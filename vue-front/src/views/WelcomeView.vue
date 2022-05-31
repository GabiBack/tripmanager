<template>
  <div class="welcomeView">
    <div :style="{backgroundImage:`url(${Background})`}" class="image" />
    <loading-spinner :showSpinner="showSpinner" />
    <div class="greetings">
      <h1>Witaj {{userName}}</h1>
      <custom-button
        label="Przejdz do moich podrózy"
        color="white"
        backgroundColor="#292015"
        @handle-click="handleButtonClick"
      />

    </div>
  </div>
</template>

<script>
import Background from '../assets/plane.png'
import LoadingSpinner from '../components/LoadingSpinner.vue'
import { ref } from 'vue'
import CustomButton from '@/components/CustomButton.vue'
const showSpinner = ref(true)
const userName = ref('')

export default {
  components: {
    LoadingSpinner,
    CustomButton
},
  methods: {
    handleButtonClick: function() {
      this.$router.push(`/trips`)
    }
  },
  data: function () {
    return {
        Background: Background,
        showSpinner: showSpinner,
        userName: userName,
    }
  },
  async mounted() {
    const {userId} = this.$route.params
    const response = await fetch(`http://localhost:8080/user/${userId}`)
    const { name } = await response.json()
    showSpinner.value = false
    userName.value = name
  }
}

</script>

<style scoped>
  .welcomeView{
    width: 100vw;
    height: 100vh;
    background-repeat: no-repeat;
    background-size: cover;
    background-image: linear-gradient(#E3DCFE 0%,#FEDCFE 35%, #FFEFE5 100%);
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .image {
    width: 800px;
    height: 450px;
    left: 10%;
    position: absolute;
    background-repeat: no-repeat;
    background-size: cover;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .greetings {
    margin-bottom: 25em;
    display: flex;
    flex-direction: column;
    align-content: center;
    z-index: 999;
  }

</style>
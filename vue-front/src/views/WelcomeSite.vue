<template>
  <div :style="{backgroundImage:`url(${gradientBackground})`}" class="gradientBackground">
    <p>TripManager</p>
      <div class="textContainer">
        <label>
          Witaj! {UserName}
        </label> 
      <div class="buttonContainer">
        <CustomButton2 @handle-click="handleClick" label="Przejdź do moich podróży"/>
      </div>
    </div>
    <img src="../assets/route.svg">
  </div>
</template>

<script>
import gradientBackground from '../assets/p2.jpg'
import CustomButton2 from '../components/CustomButton2.vue'



async function handleClick() {
    this.$router.push('/home')
  }



export default {
  methods: {
    handleClick,
  },
  components: {
    CustomButton2,
  },
  site: {
  name: 'WelcomeSite',
  },
  data: function () {
    return {
        gradientBackground: gradientBackground
    }
}
}
</script>

<style scoped>
  .gradientBackground{
    width: 100vw;
    height: 100vh;
    background-repeat: no-repeat;
    background-size: cover;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .gradientBackground p{
   top: 0;
   right: 45px;
   position: absolute;
   font-size: 20px;
  }
  .gradientBackground img{
    align-items: left;
  }
  .textContainer {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 7em;
  }
  .textContainer label{
    font-size: 25px;
    font-weight: bold;
  }
  .buttonContainer {
    font-size: 45px;
    font-weight: bold;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 7em;
  }
</style>

<template>
  <div :style="{backgroundImage:`url(${gradientBackground})`}" class="gradientBackground">
    <p>TripManager</p>
  </div>
  
</template>

<script>
import gradientBackground from '../assets/p2.jpg'




export default {
  site: {
  name: 'HomeView',
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
</style>
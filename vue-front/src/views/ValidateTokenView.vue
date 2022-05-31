<template>
  <div :style="{backgroundImage:`url(${Background})`}" class="background">
    <loading-spinner :showSpinner="showSpinner" />
  </div>
</template>

<script>
import Background from '../assets/signInBackground.png'
import LoadingSpinner from '../components/LoadingSpinner.vue'
import { ref } from 'vue'
const showSpinner = ref(true)

export default {
  components: {
    LoadingSpinner
  },
  methods: {
  },
  data: function () {
    return {
        Background: Background,
        showSpinner: showSpinner
    }
  },
  async mounted() {
    try {
      const {userId, token} = this.$route.params
      const response = await fetch(`http://localhost:8080/confirm?token=${token}&userId=${userId}`)
      const isValid = await response.json()
      if (response.status === 200 && isValid) {
        this.$router.push(`/welcome-page/${userId}`)
        return
      }
    } catch (error) {
      showSpinner.value = false
      this.$notify({
        title: "Błąd",
        type: "error",
        text: "Token niepoprawny",
      })
    }
  }
}

</script>

<style scoped>
  .background{
    width: 100vw;
    height: 100vh;
    background-repeat: no-repeat;
    background-size: cover;
    display: flex;
    align-items: center;
    justify-content: center;
  }

</style>
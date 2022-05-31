<template>
  <div class="previewImage" :style="{backgroundImage:`url(${img})`}" >
    <div class="imgLabel">
      <h3>{{label}}</h3>
    </div>
  </div>
</template>

<script setup>
  import { defineProps } from 'vue';
  defineProps({
      img: {
        type: Object,
        required: true
      },
      label: {
        type: String,
        required: true
      }
    })
</script>
<style scoped>
  .previewImage {
    border-radius: 45px;
    width: 340px;
    height: 420px;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
  }
  .imgLabel {
    border-radius: 45px;
    background: #5C5454;
    height: 150px;
    opacity: 0.9;
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
  }

</style>

<template>
  <button class="customInput" @click="$emit('handleClick')" >{{label}}</button>
</template>

<script setup>
  import { defineProps } from 'vue';
  defineProps({
      label: {
        type: String,
        required: true
      }
    })
</script>
<style scoped>
  .customInput {
    color: #2B1D12;
    background: #9E8370;
    font-size: 15px;
    border-radius: 15px;
    padding: 1em;
    border: none;
    cursor: pointer;
  }

</style>

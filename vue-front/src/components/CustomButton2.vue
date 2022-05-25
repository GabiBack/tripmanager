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
    display: block;
    width: 270px;
    margin-top: 2em;
    padding: 1em;
    background: #292015;
    border-radius: 15px;
    color: #F5F4F4;
    border: 1px solid #707070;
    outline: none;
    cursor: pointer;
    font-size: 15px;
  }

</style>

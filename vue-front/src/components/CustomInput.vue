<template>
  <input
    :type="type"
    :value="value"
    class="customInput"
    @input="(e) => $emit('handleChange', e)"
    :placeholder="placeholder"
    :style="customStyles"
  />
</template>

<script setup>
  import { defineProps } from 'vue';
    defineProps({
      placeholder: {
        type: String,
        default: ''
      },
      type: {
        type: String,
        default: 'text'
      },
      value: {
        type: String,
        default: ''
      },
      customStyles: {
        type: Object,
      },
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
  }
</style>

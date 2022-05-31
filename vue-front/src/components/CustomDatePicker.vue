<template>
  <div
    class="customInput"
    @input="(e) => $emit('handleChange', e)"
    :style="customStyles"
  >
  {{placeholder}}
    <Datepicker locale="pl" :enableTimePicker="false" v-model="date" dark @blur="$emit('date-changed', date)"></Datepicker>
  </div>
</template>

<script setup>
  import { defineProps, ref } from 'vue';
  import Datepicker from '@vuepic/vue-datepicker';
  import '@vuepic/vue-datepicker/dist/main.css'

  const date = ref();


    defineProps({
      placeholder: {
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

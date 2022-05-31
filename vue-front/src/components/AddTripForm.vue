<template>
  <div class="addTripFormContainer">
    <custom-input
      placeholder="Miejsce"
      :customStyles="{width: '45%'}"
      @handle-change="handleInputChange"
    />
    <custom-date-picker
      placeholder="Data rozpoczęcia"
      :customStyles="{width: '45%'}"
      @date-changed="(e) => handleDateChange('startDate', e)"
    />
    <custom-date-picker
      placeholder="Data zakończenia"
      :customStyles="{width: '45%'}"
      @date-changed="(e) => handleDateChange('endDate', e)"
    />
    <div class="buttonContainer">
      <custom-button
        label="Dodaj"
        @click="addTrip"
        :disabled="[formValues.place, formValues.startDate, formValues.endDate].some(item => item.length == 0)"
      />
    </div>
  </div>
</template>

<script>
import CustomInput from './CustomInput.vue';
import CustomButton from './CustomButton.vue';
import CustomDatePicker from './CustomDatePicker.vue';

import { ref } from 'vue';

const formValues = ref({ place: '', endDate: '', startDate: '' })

export default {
  components: {
    CustomInput,
    CustomButton,
    CustomDatePicker
},
  methods: {
    addTrip: async function() {
      console.log(formValues.value)
      const response = await fetch('http://localhost:8080/trip/', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formValues.value),
      });
      console.log(response);
      this.$emit('refresh-trips');
    },
    handleInputChange: function(e) {
      const values = formValues.value
      values.place = e.target.value
      formValues.value = values
    },
    handleDateChange: function(type, e) {
      const values = formValues.value
      values[type] = e.toISOString().split('T')[0]
      formValues.value = values
    }
  },
  data: function () {
    return {
      formValues: formValues,
    }
  },
  async mounted() {
  }
}

</script>
<style scoped>
  .addTripFormContainer {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    padding: 1em;
  }
  .buttonContainer {
    display: flex;
    align-items: flex-end;
  }
</style>



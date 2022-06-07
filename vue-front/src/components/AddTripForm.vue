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
      @date-changed="(e) => handleDateChange('dateStart', e)"
    />
    <custom-date-picker
      placeholder="Data zakończenia"
      :customStyles="{width: '45%'}"
      @date-changed="(e) => handleDateChange('dateEnd', e)"
    />
    <custom-input
      type="file"
      placeholder="Image"
      :customStyles="{width: '45%'}"
      @handle-change="handleImageChange"
    />
    <div class="buttonContainer">
      <custom-button
        label="Dodaj"
        @click="addTrip"
        :disabled="[formValues.place, formValues.dateStart, formValues.dateEnd].some(item => item.length == 0)"
      />
    </div>
  </div>
</template>

<script>
import CustomInput from './CustomInput.vue';
import CustomButton from './CustomButton.vue';
import CustomDatePicker from './CustomDatePicker.vue';

import { ref } from 'vue';

const formValues = ref({ place: '', dateEnd: '', dateStart: '' })
const image = ref({})

export default {
  components: {
    CustomInput,
    CustomButton,
    CustomDatePicker
},
  methods: {
    addTrip: async function() {
      const trip = await fetch('http://localhost:8080/trip/', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formValues.value),
      });
      const { id } = await trip.json()

      if(image.value.name) {
        const form = new FormData();
        form.append("file", image.value);
        try {
          await fetch(`http://localhost:8080/upload/file/${id}`, {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
            },
            body: form,
        });
        } catch (error) {
          this.$notify({
            title: "Plik za duzy",
            type: "error",
            text: "Ogarnij sie pik nie moze być wiekszy niz 1048576 bajtów",
          })
        }
      }
      this.$emit('refresh-trips');
    },
    handleInputChange: function(e) {
      const values = formValues.value
      values.place = e.target.value
      formValues.value = values
    },
    handleImageChange: function(e) {
      image.value = e.target.files[0]
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



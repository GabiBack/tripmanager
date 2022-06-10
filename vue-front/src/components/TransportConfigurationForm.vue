<template>
  <div class="transportConfigurationFormContainer">
    <custom-input
      :value="formValues.name"
      placeholder="Nazwa transportu"
      :customStyles="{width: '45%'}"
      @handle-change="(e) => handleInputChange('name', e)"
    />
    <custom-date-picker
      :value="formValues.startDate"
      placeholder="Data odjazdu"
      :customStyles="{width: '45%'}"
      @date-changed="(e) => handleDateChange('startDate', e)"
    />
    <custom-input
      :value="formValues.startPlace"
      placeholder="Miejsce odjazdu"
      :customStyles="{width: '45%'}"
      @handle-change="(e) => handleInputChange('startPlace', e)"
    />
    <custom-date-picker
      :value="formValues.endDate"
      placeholder="Data przyjazdu"
      :customStyles="{width: '45%'}"
      @date-changed="(e) => handleDateChange('endDate', e)"
    />
    <custom-input
      :value="formValues.endPlace"
      placeholder="Miejsce przyjazdu"
      :customStyles="{width: '45%'}"
      @handle-change="(e) => handleInputChange('endPlace', e)"
    />
    <custom-input
      :value="formValues.order"
      placeholder="Kolejnosc transportu"
      :customStyles="{width: '45%', height: '60px'}"
      @handle-change="(e) => handleInputChange('order', e)"
    />
    <custom-text-area
      :value="formValues.notes"
      placeholder="Notatka"
      :customStyles="{width: '100%'}"
      @handle-change="(e) => handleInputChange('notes', e)"
    />
    <div class="buttonContainer">
      <custom-button
        :label="this.activeTransportId ? 'Edytuj' : 'Dodaj'"
        @click="addTransport($route.params)"
        :disabled="[formValues.name, formValues.startDate, formValues.startPlace,
                    formValues.endDate, formValues.order].some(item => item.length == 0)"
      />
    </div>
  </div>
</template>

<script>
import CustomButton from './CustomButton.vue';
import CustomDatePicker from './CustomDatePicker.vue';
import CustomInput from './CustomInput.vue';
import CustomTextArea from './CustomTextArea.vue';

import { ref } from 'vue';
const formValues = ref({ name: '', startDate: '', startPlace: '', endDate: '', order: '' })
export default {
  components: {
    CustomButton,
    CustomDatePicker,
    CustomInput,
    CustomTextArea
},
  methods: {
    addTransport: async function(routeParams) {
      // if activeTransportid is passed to component update action is called otherwise create action
      if(this.activeTransportId) {
        await fetch(`http://localhost:8080/transport/${this.activeTransportId}`, {
          method: 'PUT',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(formValues.value),
        });
      } else {
        const { id } = routeParams
        await fetch(`http://localhost:8080/transport/${id}`, {
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(formValues.value),
        });
      }
      this.$emit('refresh-transports');
    },
    handleInputChange: function(type, e) {
      const values = formValues.value
      values[type] = e.target.value
      formValues.value = values
    },
    handleDateChange: function(type, e) {
      const values = formValues.value
      values[type] = e.toISOString().split('T')[0]
      formValues.value = values
    }
  },
  props: ['activeTransportId'],
  data: function () {
    return {
      formValues: formValues,
    }
  },
  async mounted() {
    if(this.activeTransportId) {
      const response = await fetch(`http://localhost:8080/transport/${this.activeTransportId}`)
      const body = await response.json()
      formValues.value = body
    }
  }
}

</script>
<style scoped>
  .transportConfigurationFormContainer {
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



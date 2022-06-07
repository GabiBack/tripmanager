<template>
  <div class="arrangementsContainer">
    <div class="arrangements">
      <div class="col">
        <custom-button label="Dodaj to do" @handle-click="toDoHandleModal(true)" />
        <div class="cell" v-for="({id, note}) in toDo" v-bind:key="id">
          <span>{{note}}</span>
          <a class="close" @click="removeArrangement(id)" />
        </div>
      </div>
      <div class="col">
        <custom-button label="Dodaj co zabrać" @handle-click="toTakeHandleModal(true)" />
        <div class="cell" v-for="({id, note}) in toTake" v-bind:key="id">
          <span>{{note}}</span>
          <a class="close" @click="removeArrangement(id)" />
        </div>
      </div>
      <custom-modal :show-modal="toDoModalOpen" @close-modal="toDoHandleModal(false)" title="Dodaj to do">
        <add-arrangement-form @refresh-arrangements="refreshArrangements" type="toDo" :tripId="tripId" />
      </custom-modal>
      <custom-modal :show-modal="toTakeModalOpen" @close-modal="toTakeHandleModal(false)" title="Dodaj co zabrać">
        <add-arrangement-form @refresh-arrangements="refreshArrangements" type="toTake" :tripId="tripId" />
      </custom-modal>
    </div>
  </div>
</template>

<script>
import RomaPreview from '../assets/romaPreview.png'
import LondonPreview from '../assets/londonPreview.png'
import OmanPreview from '../assets/omanPreview.png'
import CustomButton from '@/components/CustomButton.vue'
import CustomModal from '@/components/CustomModal.vue'
import AddArrangementForm from '@/components/AddArrangementForm.vue'

import { ref } from 'vue'
const toDo = ref([]);
const toTake = ref([]);
const toDoModalOpen = ref(false)
const toTakeModalOpen = ref(false)

async function fetchArrangements(id) {
  const toTakeresponse = await fetch(`http://localhost:8080/arrangements/1/${id}`)
  const toTakeBody = await toTakeresponse.json()
  const toDoresponse = await fetch(`http://localhost:8080/arrangements/2/${id}`)
  const toDoBody = await toDoresponse.json()
  toDo.value = toDoBody
  toTake.value = toTakeBody
}

async function fetchTripDetails(id) {
  const response = await fetch(`http://localhost:8080/trip/${id}`)
  const body = await response.json()
  const daysToTripStart = parseInt((new Date(body.dateStart) - Date.now())  / (1000 * 3600 * 24)) + 1
  console.log(daysToTripStart)
}

export default {
  components: {
    CustomButton,
    CustomModal,
    AddArrangementForm,
},
  methods: {
    toDoHandleModal: function(value) {
      toDoModalOpen.value = value;
    },
    toTakeHandleModal: function(value) {
      toTakeModalOpen.value = value;
    },
    refreshArrangements: function() {
      toDoModalOpen.value = false;
      toTakeModalOpen.value = false;
      const { id } = this.$route.params
      fetchArrangements(id)
    },
    removeArrangement: async function(arrangementId) {
      await fetch(`http://localhost:8080/arrangement/${arrangementId}`, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
      });
      const { id } = this.$route.params
      fetchArrangements(id)
    },
  },
  data: function () {
    return {
        RomaPreview: RomaPreview,
        LondonPreview: LondonPreview,
        OmanPreview: OmanPreview,
        toDo,
        toTake,
        toDoModalOpen,
        toTakeModalOpen,
        tripId: this.$route.params.id,
    }
  },
  async mounted() {
    const { id } = this.$route.params
    fetchArrangements(id)
    fetchTripDetails(id)
  }
}

</script>

<style scoped>
  .arrangementsContainer {
    width: 100%;
    background-repeat: no-repeat;
    background-size: cover;
    background-image: linear-gradient(#FFEDE4 0%,#FEC8FC 30%, #E1DDFE 100%);
  }
  .arrangements {
    max-width: 1200px;
    margin: auto;
    display: flex;
    justify-content: space-between;
  }

  .cell {
    position: relative;
    margin-top: 1em;
    background-color: #E4CEF9;
    border-radius: 10px;
    padding: 20px;
    display: flex;
    justify-content: space-between;
  }
  .col {
    width: 25%;
  }

  .close {
    position: absolute;
    right: 10px;
    top: 15px;
    width: 32px;
    height: 32px;
    opacity: 0.5;
    cursor: pointer;
  }
  .close:hover {
    opacity: 1;
  }
  .close:before, .close:after {
    position: absolute;
    left: 15px;
    content: ' ';
    height: 33px;
    width: 2px;
    background-color: #9e8370;
  }
  .close:before {
    transform: rotate(45deg);
  }
  .close:after {
    transform: rotate(-45deg);
  }


</style>
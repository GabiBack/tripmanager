<template>
  <div class="previewSection">
    <div class="imagesContainer">
      <trip-preview-image v-for="{id, image, place} of tripsWithImages" :img="image" :label="place" v-bind:key="id"/>

    </div>
  </div>
  <div class="listSectionContainer">
    <div class="listSection">
      <h3 class="addTripTitle">
        Dodaj podróz
      </h3>
      <table class="tripsTable">
        <tr>
          <th><span>Miejsce</span></th>
          <th><span>Dzień rozpoczęcia</span></th>
          <th><span>Dzień zakończenia</span></th>
        </tr>
        <tr v-for="({ place, id, dateStart, dateEnd }) in trips" v-bind:key="id" @click="$router.push(`/trips/${id}`)">
          <td><span>{{place}}</span></td>
          <td><span>{{dateStart}}</span></td>
          <td><span>{{dateEnd}}</span></td>
        </tr>
      </table>
      <div class="buttonContainer">
        <custom-button label="Dodaj" @handle-click="openModal" />
      </div>
        <custom-modal :show-modal="modalOpen" @close-modal="closeModal" title="Dodaj podróż">
          <add-trip-form @refresh-trips="refreshTrips" />
        </custom-modal>
    </div>
  </div>
</template>

<script>
import RomaPreview from '../assets/romaPreview.png'
import LondonPreview from '../assets/londonPreview.png'
import OmanPreview from '../assets/omanPreview.png'
import TripPreviewImage from '@/components/TripPreviewImage.vue'
import CustomButton from '@/components/CustomButton.vue'
import CustomModal from '@/components/CustomModal.vue'
import AddTripForm from '@/components/AddTripForm.vue'

import { ref } from 'vue'
const trips = ref([])
const tripsWithImages = ref([])

function readFileAsync(file) {
  return new Promise((resolve, reject) => {
    let reader = new FileReader();

    reader.onload = () => {
      resolve(reader.result);
    };

    reader.onerror = reject;

    reader.readAsDataURL(file);
  })
}

const modalOpen = ref(false)
async function fetchTrips() {
  const response = await fetch(`http://localhost:8080/trips`)
  const body = await response.json()
  trips.value = body

  // pickup first 3 trips with images for page header
  const withImages = []
  for(const {id, place} of body.slice(0,3)) {
    const imageResponse = await fetch(`http://localhost:8080/file/${id}`)
    const file = await imageResponse.blob()
    const base64data = await readFileAsync(file)
    withImages.push({id, place, image: base64data})
  }
  tripsWithImages.value = withImages
}

export default {
  components: {
    TripPreviewImage,
    CustomButton,
    CustomModal,
    AddTripForm,
},
  methods: {
    closeModal: function() {
      modalOpen.value = false;
    },
    openModal: function() {
      modalOpen.value = true;
    },
    refreshTrips: function() {
      modalOpen.value = false;
      fetchTrips()
    },
  },
  data: function () {
    return {
        RomaPreview: RomaPreview,
        LondonPreview: LondonPreview,
        OmanPreview: OmanPreview,
        trips: trips,
        modalOpen: modalOpen,
        tripsWithImages,
    }
  },
  async mounted() {
    fetchTrips()
  }
}

</script>

<style scoped>
  .previewSection {
    width: 100%;
    background-repeat: no-repeat;
    background-size: cover;
    background-image: linear-gradient(#E1DDFE 0%,#FEC8FC 30%, #FFEDE4 100%);
    display: flex;
    justify-content: space-between;
  }
  .listSectionContainer {
    width: 100%;
    background-repeat: no-repeat;
    background-size: cover;
    background-image: linear-gradient(#FFEDE4 0%,#FEC8FC 30%, #E1DDFE 100%);
  }
  .imagesContainer {
    margin: auto;
    width: 100%;
    padding: 3em;
    display: flex;
    align-items: center;
    justify-content: space-between;
    max-width: 1200px;
  }
  .addTripTitle {
    margin: 0px;
    font-weight: normal;
    color: #707070;
    padding-top: 1em;

  }
  .listSection {
    max-width: 1200px;
    margin: auto;
  }
  .tripsTable {
    margin-top: 2em;
    width: 100%;
    text-align: left;
    color: #707070;
    border-collapse: separate;
    border-spacing: 1em;
  }

  .tripsTable th span {
    border-bottom: 8px solid #707070;
    display: block;
    margin-right: 1em;
    padding: 10px;
    font-weight: normal;
  }
  .tripsTable td {
    width: 33%;
  }
  .tripsTable tr {
    margin-top: 2em;
    cursor: pointer;
  }
  .tripsTable td span{
    margin-right: 1em;
    background-color: #E4CEF9;
    border-radius: 10px;
    padding: 20px;
    display: block;
  }
  .buttonContainer {
    display: flex;
    justify-content: flex-end;
    margin-right: 2em;
    padding-bottom: 3em;
  }
</style>
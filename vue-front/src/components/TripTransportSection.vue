<template>
  <div class="container-fluid">
    <div class="previewSection">
      <div class="col-md-12">
        <div class="jumbotron bg-grey">
          <h1 class="display-4">{{this.place}}</h1>
          <p class="lead">Do twojej podrózy pozostalo <span>{{this.daysToStart}} dni</span></p>
        </div>
      </div>
      <div class="tableContainer">
        <custom-button label="Dodaj trasport" @handle-click="() => { setModal(true); setAciveTransport(0) }" backgroundColor="grey" />
        <table class="table table-dark">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Nazwa</th>
              <th scope="col">Data odjazdu</th>
              <th scope="col">Miejsce odjazdu</th>
              <th scope="col">Data przyjazdu</th>
              <th scope="col">Miejsce przyjazdu</th>
              <th scope="col">Notatki</th>
              <th scope="col">Edycja</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="({id, name, startDate, startPlace, endDate, endPlace, notes}, index) in trips" v-bind:key="id">
              <th scope="row">{{index + 1}}</th>
              <td>{{name}}</td>
              <td>{{startDate}}</td>
              <td>{{startPlace}}</td>
              <td>{{endDate}}</td>
              <td>{{endPlace}}</td>
              <td>{{notes}}</td>
              <td>
                <table-buttons
                  @handleClickRemove="removeTransport(id)"
                  @handleClickEdit="() => { setAciveTransport(id); setModal(true) }"
                />
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <custom-modal :show-modal="showMoal" title="Transport" @close-modal="() => setModal(false)">
    <transport-configuration-form @refresh-transports="refreshTrips" :activeTransportId="activeTransportId" />
  </custom-modal>
</template>

<script>
import CustomButton from '@/components/CustomButton.vue'
import TableButtons from '@/components/TableButtons.vue'
import CustomModal from './CustomModal.vue'
import TransportConfigurationForm from './TransportConfigurationForm.vue'
import { ref } from 'vue'
const trips = ref([])
const showMoal = ref(false)
const activeTransportId = ref(0)

async function fetchTransport(tripId) {
  const response = await fetch(`http://localhost:8080/transports/${tripId}`)
  const body = await response.json()
  trips.value = body
}

export default {
  components: {
    CustomButton,
    TableButtons,
    CustomModal,
    TransportConfigurationForm,
},
  props: ['daysToStart', 'place'],
  methods: {
    setModal: function(value) {
      showMoal.value = value
    },
    setAciveTransport: function(value) {
      activeTransportId.value = value
    },
    refreshTrips: function() {
      showMoal.value = false
      const { id } = this.$route.params
      fetchTransport(id)
    },
    removeTransport: async function(tripId) {
      await fetch(`http://localhost:8080/transport/${tripId}`, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
      });
      const { id } = this.$route.params
      fetchTransport(id)
    }
  },
  data: function () {
    return {
      trips,
      showMoal,
      activeTransportId
    }
  },
  async mounted() {
    const { id } = this.$route.params
    fetchTransport(id)
  }
}
</script>

<style scoped>
  .previewSection {
    background-repeat: no-repeat;
    background-size: cover;
    background-image: linear-gradient(#E1DDFE 0%,#FEC8FC 30%, #FFEDE4 100%);
  }
  .listSectionContainer {
    background-repeat: no-repeat;
    background-size: cover;
    background-image: linear-gradient(#FFEDE4 0%,#FEC8FC 30%, #E1DDFE 100%);
  }
  .container-fluid > .row {
    background-color: #FFEDE4;
  }
  .jumbotron {
    background-color: #ccaaccaa;
    margin: 0em 2em;
    padding: 2em;
    border-radius: 38px;
  }

  .tableContainer {
    margin: auto;
    max-width: 1200px;
    padding-bottom: 2em;
  }
  .tableContainer table{
    margin-top: 1em;
    margin-bottom: 0px;
  }

  /* There is a problem with applying bootstrap styles only to a single component so I have copied some bootstrap styles used above. */
  .table{width:100%;margin-bottom:1rem;color:#212529}.table td,.table th{padding:.75rem;vertical-align:top;border-top:1px solid #dee2e6}.table thead th{vertical-align:bottom;border-bottom:2px solid #dee2e6}.table tbody+tbody{border-top:2px solid #dee2e6}.table-sm td,.table-sm th{padding:.3rem}.table-bordered{border:1px solid #dee2e6}.table-bordered td,.table-bordered th{border:1px solid #dee2e6}.table-bordered thead td,.table-bordered thead th{border-bottom-width:2px}.table-borderless tbody+tbody,.table-borderless td,.table-borderless th,.table-borderless thead th{border:0}.table-striped tbody tr:nth-of-type(odd){background-color:rgba(0,0,0,.05)}.table-hover tbody tr:hover{color:#212529;background-color:rgba(0,0,0,.075)}.table-primary,.table-primary>td,.table-primary>th{background-color:#b8daff}.table-primary tbody+tbody,.table-primary td,.table-primary th,.table-primary thead th{border-color:#7abaff}.table-hover .table-primary:hover{background-color:#9fcdff}.table-hover .table-primary:hover>td,.table-hover .table-primary:hover>th{background-color:#9fcdff}.table-secondary,.table-secondary>td,.table-secondary>th{background-color:#d6d8db}.table-secondary tbody+tbody,.table-secondary td,.table-secondary th,.table-secondary thead th{border-color:#b3b7bb}.table-hover .table-secondary:hover{background-color:#c8cbcf}.table-hover .table-secondary:hover>td,.table-hover .table-secondary:hover>th{background-color:#c8cbcf}.table-success,.table-success>td,.table-success>th{background-color:#c3e6cb}.table-success tbody+tbody,.table-success td,.table-success th,.table-success thead th{border-color:#8fd19e}.table-hover .table-success:hover{background-color:#b1dfbb}.table-hover .table-success:hover>td,.table-hover .table-success:hover>th{background-color:#b1dfbb}.table-info,.table-info>td,.table-info>th{background-color:#bee5eb}.table-info tbody+tbody,.table-info td,.table-info th,.table-info thead th{border-color:#86cfda}.table-hover .table-info:hover{background-color:#abdde5}.table-hover .table-info:hover>td,.table-hover .table-info:hover>th{background-color:#abdde5}.table-warning,.table-warning>td,.table-warning>th{background-color:#ffeeba}.table-warning tbody+tbody,.table-warning td,.table-warning th,.table-warning thead th{border-color:#ffdf7e}.table-hover .table-warning:hover{background-color:#ffe8a1}.table-hover .table-warning:hover>td,.table-hover .table-warning:hover>th{background-color:#ffe8a1}.table-danger,.table-danger>td,.table-danger>th{background-color:#f5c6cb}.table-danger tbody+tbody,.table-danger td,.table-danger th,.table-danger thead th{border-color:#ed969e}.table-hover .table-danger:hover{background-color:#f1b0b7}.table-hover .table-danger:hover>td,.table-hover .table-danger:hover>th{background-color:#f1b0b7}.table-light,.table-light>td,.table-light>th{background-color:#fdfdfe}.table-light tbody+tbody,.table-light td,.table-light th,.table-light thead th{border-color:#fbfcfc}.table-hover .table-light:hover{background-color:#ececf6}.table-hover .table-light:hover>td,.table-hover .table-light:hover>th{background-color:#ececf6}.table-dark,.table-dark>td,.table-dark>th{background-color:#c6c8ca}.table-dark tbody+tbody,.table-dark td,.table-dark th,.table-dark thead th{border-color:#95999c}.table-hover .table-dark:hover{background-color:#b9bbbe}.table-hover .table-dark:hover>td,.table-hover .table-dark:hover>th{background-color:#b9bbbe}.table-active,.table-active>td,.table-active>th{background-color:rgba(0,0,0,.075)}.table-hover .table-active:hover{background-color:rgba(0,0,0,.075)}.table-hover .table-active:hover>td,.table-hover .table-active:hover>th{background-color:rgba(0,0,0,.075)}.table .thead-dark th{color:#fff;background-color:#343a40;border-color:#454d55}.table .thead-light th{color:#495057;background-color:#e9ecef;border-color:#dee2e6}.table-dark{color:#fff;background-color:#343a40}.table-dark td,.table-dark th,.table-dark thead th{border-color:#454d55}.table-dark.table-bordered{border:0}.table-dark.table-striped tbody tr:nth-of-type(odd){background-color:rgba(255,255,255,.05)}.table-dark.table-hover tbody tr:hover{color:#fff;background-color:rgba(255,255,255,.075)}@media (max-width:575.98px){.table-responsive-sm{display:block;width:100%;overflow-x:auto;-webkit-overflow-scrolling:touch}.table-responsive-sm>.table-bordered{border:0}}@media (max-width:767.98px){.table-responsive-md{display:block;width:100%;overflow-x:auto;-webkit-overflow-scrolling:touch}.table-responsive-md>.table-bordered{border:0}}@media (max-width:991.98px){.table-responsive-lg{display:block;width:100%;overflow-x:auto;-webkit-overflow-scrolling:touch}.table-responsive-lg>.table-bordered{border:0}}@media (max-width:1199.98px){.table-responsive-xl{display:block;width:100%;overflow-x:auto;-webkit-overflow-scrolling:touch}.table-responsive-xl>.table-bordered{border:0}}.table-responsive{display:block;width:100%;overflow-x:auto;-webkit-overflow-scrolling:touch}

</style>
/**
 * Stolen from https://developers.google.com/maps/documentation/javascript/examples/places-autocomplete-addressform
 */

$(function() {
	$('body').attr('onload', 'initialize();');

	$('#address').attr('onfocus', 'geolocate();');
	$('#address').attr('onkeypress', 'enterKeyPressDontSubmit(event);;');
});

// This example displays an address form, using the autocomplete feature
// of the Google Places API to help users fill in the information.

var autocomplete;
//var componentForm = {
//  street_number: 'short_name',
//  route: 'long_name',
//  locality: 'long_name',
//  administrative_area_level_1: 'short_name',
//  country: 'long_name',
//  postal_code: 'short_name'
//};

function initialize() {
//	var element = 'autocomplete';
	var element = 'address';
  // Create the autocomplete object, restricting the search
  // to geographical location types.
  autocomplete = new google.maps.places.Autocomplete(
      /** @type {HTMLInputElement} */(document.getElementById(element)),
      { types: ['geocode'] });
  // When the user selects an address from the dropdown,
  // populate the address fields in the form.
//  google.maps.event.addListener(autocomplete, 'place_changed', function() {
//    fillInAddress();
//  });
}

function fillInAddress() {
//  // Get the place details from the autocomplete object.
//  var place = autocomplete.getPlace();
//
//  for (var component in componentForm) {
//    document.getElementById(component).value = '';
//    document.getElementById(component).disabled = false;
//  }
//
//  // Get each component of the address from the place details
//  // and fill the corresponding field on the form.
//  for (var i = 0; i < place.address_components.length; i++) {
//    var addressType = place.address_components[i].types[0];
//    if (componentForm[addressType]) {
//      var val = place.address_components[i][componentForm[addressType]];
//      document.getElementById(addressType).value = val;
//    }
//  }
}

// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var geolocation = new google.maps.LatLng(
          position.coords.latitude, position.coords.longitude);
      autocomplete.setBounds(new google.maps.LatLngBounds(geolocation,
          geolocation));
    });
  }
}

// Don't submit when pressing the enter
// Ref.: https://stackoverflow.com/questions/19103350/dont-submit-form-when-i-press-enter-key-in-a-textbox
function enterKeyPressDontSubmit(event)
{
	if (event.keyCode === 13) {
		event.preventDefault();
	}
}

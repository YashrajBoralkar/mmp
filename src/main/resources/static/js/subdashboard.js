
  
  src="https://code.jquery.com/jquery-3.6.0.min.js">

  //EmployeeRegistration
  function submitForm() {
  	    const formData = new FormData(document.getElementById('employeeForm'));
  	    const id = $('#id').val();

  	    let url, successMessage;

  	    if (!id) {
  	        url = '/employee_registration';
  	        successMessage = 'Enrollment saved successfully!';
  	    } else {
  	        url = '/update_employee';
  	        successMessage = 'Enrollment updated successfully!';
  	    }

  	    $.ajax({
  	        url: url,
  	        type: 'POST',
  	        data: formData,
  	        processData: false,
  	        contentType: false,
  	        success: function(response) {
  	            $('#message').text(successMessage); // Corrected this line
  	            // Optionally, reset the form
  	            resetForm();
  	            // Optionally, redirect after successful form submission
  	            window.location.href = '/view_all_employees';
  	        },
  	        error: function() {
  	            alert('Error saving enrollment. Please try again.');
  	        }
  	    });
  	}


  function resetForm() {
      $('#employeeForm')[0].reset();
      $('#id').val('');
      $('#message').text('');
      $('#form-title').text('New Enrollment');
      $('#submitBtn').text('Submit');

      }


      function prefillForm(data) {
          $('#id').val(data.id);
          $('#employeeUID').val(data.employeeUID);
          $('#firstName').val(data.firstName);
          $('#middleName').val(data.middleName);
          $('#lastName').val(data.lastName);

          $('#dateOfBirth').val(data.dateOfBirth);
          $('#gender').val(data.gender);
          $('#maritalStatus').val(data.maritalStatus);
          $('#nationality').val(data.nationality);
          $('#departmentName').val(data.departmentName);

          $('#permanentAddressLine1').val(data.permanentAddressLine1);
          $('#permanentAddressLine2').val(data.permanentAddressLine2);
          $('#permanentCity').val(data.permanentCity);
          $('#permanentState').val(data.permanentState);
          $('#permanentCountry').val(data.permanentCountry);
          $('#permanentZipCode').val(data.permanentZipCode);

          $('#currentAddressLine1').val(data.currentAddressLine1);
          $('#currentAddressLine2').val(data.currentAddressLine2);
          $('#currentCity').val(data.currentCity);
          $('#currentState').val(data.currentState);
          $('#currentCountry').val(data.currentCountry);
          $('#currentZipCode').val(data.currentZipCode);

          $('#primaryContactNumber').val(data.primaryContactNumber);
          $('#alternateContactNumber').val(data.alternateContactNumber);
          $('#personalEmail').val(data.personalEmail);
          $('#bloodGroup').val(data.bloodGroup);

          $('#emergencyContactName').val(data.emergencyContactName);
          $('#emergencyRelationship').val(data.emergencyRelationship);
          $('#emergencyContactNumber').val(data.emergencyContactNumber);
          $('#emergencyEmail').val(data.emergencyEmail);

          $('#passportNumber').val(data.passportNumber);
          $('#visaNumber').val(data.visaNumber);

          $('#visaExpiryDate').val(data.visaExpiryDate);

          // For file inputs, you cannot set a value via JavaScript directly for security reasons.
          // Instead, you would typically show the current file's name or provide an option to upload a new one.

          $('#aadharSsnFile').text(data.aadharSsnFile);
          $('#uploadProfilePhotoFile').text(data.uploadProfilePhotoFile);
          $('#panTaxIdFile').text(data.panTaxIdFile);
          $('#resumeFile').text(data.resumeFile);
          $('#educationCertificatesFile').text(data.educationCertificatesFile);
          $('#professionalCertificatesFile').text(data.professionalCertificatesFile);

          $('#form-title').text('Update Employee Details');
          $('#submitBtn').text('Update');
      }

	  // Example JavaScript to handle search functionality
	  document.querySelector('button').addEventListener('click', function() {
	      const query = document.getElementById('search').value;
	      alert('Searching for: ' + query);
	    });
  
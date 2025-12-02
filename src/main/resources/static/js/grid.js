function loadForm(formFile) {
    // Fetch the content of the form and load it into the form container
    fetch('forms/' + formFile)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .then(data => {
            const formContainer = document.getElementById('form-container');
            formContainer.innerHTML = data; // Load form HTML into the container
        })
        .catch(error => {
            console.error('Error loading the form:', error);
            alert('Failed to load the form. Please try again.');
        });
}

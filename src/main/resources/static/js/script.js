const uploadForm = document.getElementById("fileUploadForm");

if (uploadForm) {

    uploadForm.addEventListener("submit", async function (event) {

        event.preventDefault();

        const fileInput = document.getElementById("fileInput");
        const message = document.getElementById("uploadResult");

        message.innerHTML = "Uploading...";

        const formData = new FormData();

        formData.append("file", fileInput.files[0]);

        try {

            const response = await fetch("/api/datasets", {
                method: "POST",
                body: formData
            });

            const data = await response.json();

            if (!response.ok) {
                throw new Error(data.error || "Upload failed");
            }

            message.innerHTML = `
        <strong>Upload Successful</strong><br><br>

        Dataset ID: ${data.datasetId}<br>
        File: ${data.fileName}<br>
        Rows: ${data.rowCount}<br>
        Columns: ${data.columnCount}<br>
        Status: ${data.status}
    `;

        } catch (error) {

            message.innerHTML = error.message;

        }

    });

}
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Assunto do E-mail</title>
    <style>
      body {
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
        font-family: Arial, sans-serif;
      }
      table {
        border-spacing: 0;
        width: 100%;
        max-width: 600px;
        margin: 0 auto;
        background-color: #ffffff;
        border: 1px solid #ddd;
      }
      td {
        padding: 15px;
      }
      .header {
        background-color: #0073e6;
        color: #ffffff;
        text-align: center;
        padding: 20px;
        font-size: 24px;
        font-weight: bold;
      }
      .footer {
        background-color: #f4f4f4;
        color: #555555;
        text-align: center;
        padding: 10px;
        font-size: 12px;
      }
    </style>
  </head>
  <body>
    <table role="presentation">
      <tr>
        <td class="header">${assunto}</td>
      </tr>

      <tr>
        <td>
          <p>Olá, ${nome}!</p>
          <p>Seu endereço foi criado com sucesso.</p>
          <p>Se precisar de ajuda, entre em contato conosco.</p>
        </td>
      </tr>

      <tr>
        <td class="footer">
          <p>© 2024 VEM SER DBC. Todos os direitos reservados.</p>
        </td>
      </tr>
    </table>
  </body>
</html>

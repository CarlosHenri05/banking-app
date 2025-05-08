package br.com.banking.app.transaction.model;

public enum Category {
    // Receitas
    SALARIO,           // Salário
    BONUS,            // Bônus
    INVESTIMENTOS,    // Rendimentos de investimentos
    OUTRAS_RECEITAS,  // Outras receitas

    // Despesas Fixas
    MORADIA,          // Aluguel, condomínio, IPTU
    ALIMENTACAO,      // Supermercado, restaurantes
    TRANSPORTE,       // Combustível, transporte público
    SAUDE,            // Plano de saúde, medicamentos
    EDUCACAO,         // Mensalidades, cursos
    SERVICOS,         // Água, luz, internet, telefone

    // Despesas Variáveis
    LAZER,            // Entretenimento, hobbies
    VESTUARIO,        // Roupas, calçados
    VIAGENS,          // Viagens e passeios
    PRESENTES,        // Presentes e doações
    BELEZA,           // Cuidados pessoais
    TECNOLOGIA,       // Gadgets, eletrônicos

    // Outros
    OUTRAS_DESPESAS   // Outras despesas não categorizadas
}

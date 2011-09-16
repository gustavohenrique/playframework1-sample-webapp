Ext.define('PoupaNiquel.model.Transaction', {
    extend: 'Ext.data.Model',
    fields: ['id', 'description', 'transactionDate', 'amount', 'balance', 'account','payee', 'category', 'payment']
});
Ext.define('PoupaNiquel.model.Transaction', {
    extend: 'Ext.data.Model',
    fields: ['id', 'description', 'date', 'amount'],
 
    proxy: {
        type: 'ajax',
        url: '/homebank/transactions',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});
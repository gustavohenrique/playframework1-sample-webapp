Ext.define('PoupaNiquel.model.Transaction', {
    extend: 'Ext.data.Model',
    fields: ['id', 'description', 'date', 'amount'],
 
    proxy: {
        type: 'ajax',
        url: 'data/recentsongs.json',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});
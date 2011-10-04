Ext.define('PoupaNiquel.model.Account', {
    extend: 'Ext.data.Model',
    fields: ['id', 'name','number','initial'],
    
    belongsTo:{model: 'PoupaNiquel.model.Transaction', associatedName:'Transaction'}
});
Ext.define('PoupaNiquel.store.Accounts', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Account',
    
    storeId: 'Accounts',
    autoLoad: true,
    pageSize: 35,
    
    proxy: {
        type: 'rest',
        api: {
        	read : '/transactions/accounts',
        },
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
    
        listeners: {
        	exception: function(proxy, response, operation){
            	var message = Ext.JSON.decode(response.responseText).message;
            	Ext.Msg.alert('Error', 'No accounts found. Please add an account.');
            }
        }
    }
});
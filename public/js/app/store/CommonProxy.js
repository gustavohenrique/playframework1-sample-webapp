Ext.define('PoupaNiquel.store.CommonProxy', {
    extend: 'Ext.data.proxy.Rest',
    
//    type: 'rest',
    	
    reader: {
        type           : 'json',
        root           : 'data',
        successProperty: 'success',
        totalProperty  : 'total'
    },
    
    writer: {
        type           : 'json',
        root           : 'data',
        writeAllFields : true,
        encode         : false,
    },
    
    listeners: {
        exception: function(proxy, response, operation){
        	var message = Ext.JSON.decode(response.responseText).message;
        	Ext.Msg.alert('Error', message);
        }
    }
});
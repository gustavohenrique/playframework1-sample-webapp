Ext.define('PoupaNiquel.store.Payees', {
    extend: 'Ext.data.Store',
    model : 'PoupaNiquel.model.Payee',
    
    autoLoad: true,
    autoSync: false,
    pageSize: 30,
    
    proxy: Ext.create('PoupaNiquel.store.CommonProxy', {
    	model: 'Payee',
    	api: {
        	read : '/payees/read',
        	create : '/payees/create',
            update: '/payees/update',
            destroy: '/payees/delete'
        },
    }),
    
});
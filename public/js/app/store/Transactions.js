Ext.define('PoupaNiquel.store.Transactions', {
    extend: 'Ext.data.Store',
    model : 'PoupaNiquel.model.Transaction',
    
    autoLoad: true,
    autoSync: false,
    pageSize: 20,
    
    proxy: Ext.create('PoupaNiquel.store.CommonProxy', {
    	model: 'Transaction',
        api: {
        	read   : '/transactions/read',
        	create : '/transactions/create',
            update : '/transactions/update',
            destroy: '/transactions/delete'
        },
    }),
});
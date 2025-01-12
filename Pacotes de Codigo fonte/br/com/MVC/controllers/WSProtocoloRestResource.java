package br.com.MVC.controllers;

import br.com.MVC.models.Clientes;
import br.com.MVC.models.Empresa;
import br.com.MVC.models.Item;
import br.com.MVC.models.ItemProtocolo;
import br.com.MVC.models.Protocolo;
import br.com.MVC.models.Usuarios;
import com.google.gson.Gson;
import java.text.ParseException;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;


@Path("WSProtocoloRest")
public class WSProtocoloRestResource {

    private final UsuariosCtrl usuariosCtrl;
    private final ProtocoloAppCtrl protocoloCtrl;
    private final Gson gson;

    public WSProtocoloRestResource() {
        usuariosCtrl = new UsuariosCtrl();
        protocoloCtrl = new ProtocoloAppCtrl();
        gson = new Gson();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("usuarios/cadastrar")
    public String cadastrarUsuarios(String usuario) {

        Usuarios user = (Usuarios) gson.fromJson(usuario, Usuarios.class);
        user = usuariosCtrl.cadastrarUsuario(user);
        return gson.toJson(user);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("usuarios/logar")
    public String logar(String login) throws JSONException {
        JSONObject loginJson = new JSONObject(login);
        String userName = loginJson.getString("userName");
        String password = loginJson.getString("password");
        Usuarios usuario = protocoloCtrl.logar(userName, password);
        return gson.toJson(usuario);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("empresa/cadastrar")
    public String cadastrarEmpresa(String empresa) {
        Empresa empresaJson = (Empresa) gson.fromJson(empresa, Empresa.class);
        Empresa empresaCadastrada = protocoloCtrl.cadastrarEmpresa(empresaJson);
        String json = gson.toJson(empresaCadastrada);
        return json;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("empresa/atualizar")
    public String atualizaEmpresa(String empresa) {
        Empresa empresaJson = (Empresa) gson.fromJson(empresa, Empresa.class);
        Empresa empresaAtualizada = protocoloCtrl.atualizaEmpresa(empresaJson);
        String json = gson.toJson(empresaAtualizada);
        return json;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("empresa/getEmpresa/{codUsuario}")
    public String getEmpresa(@PathParam("codUsuario") String codUsuario) {
        Empresa empresa = protocoloCtrl.getEmpresa(Integer.parseInt(codUsuario));
        String json = gson.toJson(empresa);
        return json;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("empresa/excluir")
    public String removeEmpresa(String idEmpresa) throws JSONException {
        JSONObject empresaJson = new JSONObject(idEmpresa);
        int idEmpresaJson = empresaJson.getInt("idEmpresa");
        String result = gson.toJson(protocoloCtrl.removeEmpresa(idEmpresaJson));
        return result;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("clientes/cadastrar")
    public String cadastrarCliente(String cliente) {
        Clientes clientesJson = (Clientes) gson.fromJson(cliente, Clientes.class);
        Clientes clienteCadastrado = protocoloCtrl.cadastrarCliente(clientesJson);
        String json = gson.toJson(clienteCadastrado);
        return json;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("clientes/getListaCLientes/{codEmpresa}")
    public String getListClientes(@PathParam("codEmpresa") String codEmpresa) {
        List<Clientes> listClientes = protocoloCtrl.getListaClientes(Integer.parseInt(codEmpresa));
        String json = gson.toJson(listClientes);
        return json;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("clientes/atualizar")
    public String atualizaCliente(String cliente) {
        Clientes clienteJson = (Clientes) gson.fromJson(cliente, Clientes.class);
        return gson.toJson(protocoloCtrl.atualizaCliente(clienteJson));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("clientes/excluir")
    public String removeCliente(String idCliente) throws JSONException {
        JSONObject clienteJson = new JSONObject(idCliente);
        int idClienteJson = clienteJson.getInt("idCliente");
        String result = gson.toJson(protocoloCtrl.removeCliente(idClienteJson));
        return result;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("itens/cadastrar")
    public String cadastrarItem(String item) {
        Item itemJson = (Item) gson.fromJson(item, Item.class);
        Item itemCadastrado = protocoloCtrl.cadastrarItem(itemJson);
        String json = gson.toJson(itemCadastrado);
        return json;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("itens/getListaItens/{codEmpresa}")
    public String getListItens(@PathParam("codEmpresa") String codEmpresa) {
        List<Item> listItens = protocoloCtrl.getListaItem(Integer.parseInt(codEmpresa));
        String json = gson.toJson(listItens);
        return json;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("itens/atualizar")
    public String atualizaItem(String item) {
        Item itemJson = (Item) gson.fromJson(item, Item.class);
        return gson.toJson(protocoloCtrl.atualizaItem(itemJson));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("itens/excluir")
    public String removeItem(String idItem) throws JSONException {
        JSONObject itemJson = new JSONObject(idItem);
        int idItemJson = itemJson.getInt("idItem");
        String result = gson.toJson(protocoloCtrl.removeItem(idItemJson));
        return result;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("protocolos/cadastrar")
    public String cadastrarProtocolo(String protocolo) throws JSONException, ParseException {
        return gson.toJson(protocoloCtrl.cadastrarProtocolo(protocolo));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("clientes/getCliente/{codCliente}")
    public String getCliente(@PathParam("codCliente") String codCliente) {
        Clientes cliente = protocoloCtrl.getCliente(Integer.parseInt(codCliente));
        String json = gson.toJson(cliente);
        return json;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("protocolos/getListaProtocolos/{codEmpresa}")
    public String getListProtocolos(@PathParam("codEmpresa") String codEmpresa) {
        List<Protocolo> listProtocolos = protocoloCtrl.getListaProtocolo(Integer.parseInt(codEmpresa));
        String json = gson.toJson(listProtocolos);
        return json;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("protocolo/excluir")
    public String removeProtocolo(String idProtocolo) throws JSONException {
        JSONObject protocoloJson = new JSONObject(idProtocolo);
        int idEmpresaJson = protocoloJson.getInt("idProtocolo");
        String result = gson.toJson(protocoloCtrl.removeProtocolo(idEmpresaJson));
        return result;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("itensProtocolos/getListaItensProtocolos/{codProtocolo}")
    public String getListItensProtocolo(@PathParam("codProtocolo") String codProtocolo) {
        List<ItemProtocolo> listItensProtocolo = protocoloCtrl.getListaItemProtocolo(Integer.parseInt(codProtocolo));
        String json = gson.toJson(listItensProtocolo);
        return json;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("protocolo/getProtocolo/{codProtocolo}")
    public String getProtocolo(@PathParam("codProtocolo") String codProtocolo) {
        Protocolo protocolo = protocoloCtrl.getProtocolo(Integer.parseInt(codProtocolo));
        String json = gson.toJson(protocolo);
        return json;
    }

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("protocolos/atualizar")
//    public String atualizaProtocolo(String protocolo) throws JSONException, ParseException {
//        return gson.toJson(protocoloCtrl.atualizaProtocolo(protocolo));
//    }
    
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("itemProtocolo/excluir")
//    public String removeItemPotocolo(String idItemProtocolo) throws JSONException {
//        JSONObject protocoloJson = new JSONObject(idItemProtocolo);
//        int idItemProtocoloJson = protocoloJson.getInt("idItemProtocolo");
//        String result = gson.toJson(protocoloCtrl.removeItemProtocolo(idItemProtocoloJson));
//        return result;
//    }
}

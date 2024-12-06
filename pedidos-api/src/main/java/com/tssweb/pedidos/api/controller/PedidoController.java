package com.tssweb.pedidos.api.controller;

import com.tssweb.pedidos.api.entity.Pedido;
import com.tssweb.pedidos.api.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pedidos", description = "API para gerenciamento de pedidos")
@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(PedidoController.class);

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Cria um novo pedido", description = "Cria um novo pedido na base de dados",
    responses = {
        @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "400", description = "Erro na requisição"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        logger.info("Pedido recebido: {}", pedido.toString());
        Pedido enfileirared = pedidoService.enfileirarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(enfileirared);
    }
}

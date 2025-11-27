package com.Ecommerce.UserAPI.Producers;


import com.Ecommerce.UserAPI.business.dto.Response.Emaildto;
import com.Ecommerce.UserAPI.business.dto.Response.UsuarioResponseDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {


    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.nome}")
    private String routingkey;

    public void publishMessageEmail(UsuarioResponseDTO usuarioResponseDTO){
        var emaildto = new Emaildto();
                emaildto.setUserId(usuarioResponseDTO.getId());
                emaildto.setEmailTo(usuarioResponseDTO.getEmail());
                emaildto.setSubject("Cadastro realizado com sucesso!");
                emaildto.setText(usuarioResponseDTO.getNome()+ ", seja bem vindo a nossa plataforma!"+ "Seu Código de Verificação é "+ usuarioResponseDTO.getVerificationCode());

        rabbitTemplate.convertAndSend("", routingkey, emaildto);
    }
}

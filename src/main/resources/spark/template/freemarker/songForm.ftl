<div class="starter-template">
    <form class="form-horizontal" role="form" id='song-create-form' method='POST' <#if song??>action="/song/update/:id"<#else>action="/song/create"</#if>>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="Name">Titulo: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="Name" name='song-Name' placeholder="Escribe el nombre" <#if song??>value="${song.getName()}"</#if> />
            </div>
        </div>
        
<div class="form-group">
            <label class="col-sm-3 control-label" for="Author">Autor: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="Author" name='song-Author' placeholder="Escribe el autor" <#if song??>value="${song.getAuthor()}"</#if> />
            </div>
        </div>

<div class="form-group">
            <label class="col-sm-3 control-label" for="Length">Duración: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="Length" name='song-Length' placeholder="Escribe la duracion" <#if song??>value="${song.getLength()}"</#if> />
            </div>
        </div>
        <#if song??>
            <input type='hidden' name='song-id' value="${song.getId()}" />
        </#if>
    </form>

    
    <input type='submit' <#if song??>value='Actualizar'<#else>value='Añadir'</#if> class="btn btn-primary" form='song-create-form' />
</div>